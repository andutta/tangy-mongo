package hello.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by anshumandutta on 10/18/14.
 */
@Document (collection = "employees")
public class Employee {
    public String empName;
    @Id
    public long empid;
    public float salary;

    @JsonDeserialize (using=DateDeserializer.class)
    @JsonSerialize (using=DateSerializer.class)
    public Date dateOfJoin;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public long getEmpid() {
        return empid;
    }

    public void setEmpid(long empid) {
        this.empid = empid;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Date getDateOfJoin() {
        return dateOfJoin;
    }

    public void setDateOfJoin(Date dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

    public static class DateDeserializer extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            ObjectCodec oc = jp.getCodec();
            JsonNode node = oc.readTree(jp);
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                return formatter.parse(node.textValue());
            }catch (ParseException pe){
                return null;
            }

        }
    }

    public static class DateSerializer extends JsonSerializer<Date> {
        @Override
        public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
            jgen.writeStartObject();
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            jgen.writeStringField("dateOfJoin", formatter.format(value));
            jgen.writeEndObject();
        }
    }
}
