package org.codeship.company.dto.departmentDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class DepartmentRequestDTO {

    @NotBlank(message = "Department name is required")
    @Size(max = 100, message = "Department name cannot exceed 100 character")
    private String deptName;

    @Positive(message = "Department head ID must be positive")
    private Long deptHeadId;

    @Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;

    @Size(max = 100, message = "Location cannot exceed 100 characters")
    private String location;

    //its default value is true in database
    private Boolean active = true;

    public DepartmentRequestDTO() {
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getDeptHeadId() {
        return deptHeadId;
    }

    public void setDeptHeadId(Long deptHeadId) {
        this.deptHeadId = deptHeadId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

//    {
//             "deptName": "Engineering",
//            "deptHeadId": 101,
//            "description": "Software development team",
//            "location": "Indore",
//            "active": true
//    }
}
