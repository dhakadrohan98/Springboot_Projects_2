    package com.wissen.url_shortening.controller;

    import com.wissen.url_shortening.model.Url;
    import com.wissen.url_shortening.model.UrlDto;
    import com.wissen.url_shortening.model.UrlErrorResponseDto;
    import com.wissen.url_shortening.model.UrlResponseDto;
    import com.wissen.url_shortening.service.UrlService;
    import io.micrometer.common.util.StringUtils;
    import jakarta.servlet.http.HttpServletResponse;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.io.IOException;
    import java.time.LocalDateTime;

    @RestController
    @RequestMapping("url")
    public class UrlShorteningController {

        private static final Logger logger = LoggerFactory.getLogger(UrlShorteningController.class);

        @Autowired
        private UrlService urlService;
        @Autowired
        private UrlResponseDto urlResponseDto;
        @Autowired
        private UrlErrorResponseDto urlErrorResponseDto;

        @Value("${server.port}")
        private String port;

        private int requestCount = 0;

        @PostMapping("/generate")
        public ResponseEntity<?> generateShortLink(@RequestBody UrlDto urlDto) {
            // Log which instance (port) is handling the request
            requestCount++;
            logger.info("Request #" + requestCount + " handled by Department Service running on port: " + port);

            Url urlToRet = urlService.generateShortLink(urlDto);
            //if url object(not null) that need to prepare(according to some format), then return UrlReponse object
            if(urlToRet != null) {
                urlResponseDto.setOriginalUrl(urlToRet.getOriginalUrl());
                urlResponseDto.setExpirationDate(urlToRet.getExpirationDate());
                urlResponseDto.setShortLink(urlToRet.getShortLink());
                return new ResponseEntity<UrlResponseDto>(urlResponseDto, HttpStatus.OK);
            }
            //If we are not able to generate the short link, So prepare url Error Reponse object & return it
            urlErrorResponseDto.setStatus("404");
            urlErrorResponseDto.setError("There was an error processing your request, " +
                    "please try again");
            return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK);
        }

        @GetMapping("/{shortLink}")
        public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortLink, HttpServletResponse response) {
            //if passed short link is empty
            if(StringUtils.isEmpty(shortLink)) {
                urlErrorResponseDto.setStatus("404");
                urlErrorResponseDto.setError("Invalid url");
                return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK);
            }

            Url urlToReturn = urlService.getEncodedUrl(shortLink);
            //if url is expired does not exist due to some other reason
            if(urlToReturn == null) {
                urlErrorResponseDto.setError("url does not exist or it might have been expired");
                urlErrorResponseDto.setStatus("400");
                return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK);
            }
            //if url has been expired
            if(urlToReturn.getExpirationDate().isBefore(LocalDateTime.now())) {
                //delete the url because it is expired
                urlService.deleteShortLink(urlToReturn);
                //set response in urlErrorReponseDto object & return this response
                urlErrorResponseDto.setError("Url has been expired. please generate a new one");
                urlErrorResponseDto.setStatus("200");
                return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK);
            }

            // Redirect to the original URL, if url is not expired
            try {
                //Sends a redirect response to the client using the specified redirect
                // location URL with the status code SC_FOUND 302 (Found), clears the
                // response buffer and commits the response
                response.sendRedirect(urlToReturn.getOriginalUrl());
            } catch (IOException e) {
                urlErrorResponseDto.setStatus("500");
                urlErrorResponseDto.setError("Error during redirection");
                return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK);
            }

            return null; // This line will never be reached
        }

        @DeleteMapping("/{shortLink}")
        public void deleteShortUrl(@PathVariable String shortLink) {
            Url urlObj = urlService.getEncodedUrl(shortLink);
            urlService.deleteShortLink(urlObj);
        }

    }
