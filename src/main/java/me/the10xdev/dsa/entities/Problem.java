package me.the10xdev.dsa.entities;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.the10xdev.dsa.models.TestCase;
import me.the10xdev.dsa.types.Category;
import me.the10xdev.dsa.types.Difficulty;
import me.the10xdev.dsa.types.IOType;
import me.the10xdev.dsa.types.ValidationType;
import org.hibernate.annotations.Type;

import java.util.List;


@Entity
@Table(name = "problems")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Problem {

    @Id
    private String id;

    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "difficulty_info")
    @Type(PostgreSQLEnumType.class)
    private Difficulty difficulty;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "category_info")
    @Type(PostgreSQLEnumType.class)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "types_info")
    @Type(PostgreSQLEnumType.class)
    private IOType outputType;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "validation_types_info")
    @Type(PostgreSQLEnumType.class)
    private ValidationType validationType;

    @Type(ListArrayType.class)
    @Column(columnDefinition = "text[]")
    private List<String> hints;

    private String optimalSpace;

    private String optimalTime;

    @Column(columnDefinition = "text")
    private String optimalMetaDescription;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<TestCase> runTestCases;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<TestCase> submitTestCases;

//    private Long expectedMemory;
//    private Long expectedTime;

    @Column(columnDefinition = "text")
    private String codeStub; // to be shown to user

    @Column(columnDefinition = "text")
    private String codeRunStub; // to be used to generate code for testing

}
