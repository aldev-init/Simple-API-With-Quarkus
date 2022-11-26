package DTO.Course;

import Models.MentorModel;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "CourseAddRequest")
public class CourseAddRequest {
    @Schema(required = true,example = "Belajar Ngepet",description = "Nama Kursus")
    public String name;

    @Schema(required = true,example = "Easy",description = "Tingkat Kesulitan Kursus")
    public String difficult;

    @Schema(required = true,example = "12.000.000",description = "Harga Kursus")
    public String price;

    @Schema(required = true,example = "1",description = "Id Mentor")
    public long mentor;
}
