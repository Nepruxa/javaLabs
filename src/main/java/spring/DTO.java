package spring;

public class DTO {
    private Integer schoolNum;
    private String schoolAddress;

    public DTO(Integer schoolNum, String schoolAddress) {
        this.schoolAddress = schoolAddress;
        this.schoolNum = schoolNum;
    }

    public Integer getSchoolNum() {
        return schoolNum;
    }

    public void setSchoolNum(Integer schoolNum) {
        this.schoolNum = schoolNum;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }
}
