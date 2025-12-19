package br.com.augustoomb.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.hateoas.RepresentationModel; // PARA USAR HATEOAS

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

// @JsonPropertyOrder({"id", "address", "first_name", "last_name", "gender"})
//@JsonFilter("PersonFilter") // USA O FILTER CRIADO EM config/ObjectMapperConfig. Na prática, o atributo "sensitiveData" não vai aparecer na resposta da API
public class PersonDTO extends RepresentationModel<PersonDTO> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

//    @JsonProperty("first_name") // MUDA O NOME. O CLIENTE RECEBE E ENVIA COM first_name ao inves de firstName
    private String firstName;

//    @JsonProperty("last_name")
//    @JsonInclude(JsonInclude.Include.NON_NULL) // SE DO BANCO VIER NULL, NÃO MOSTRA NA RESPOSTA DA API
    private String lastName;

//    @JsonInclude(JsonInclude.Include.NON_EMPTY) // SE DO BANCO VIER VAZIO, NÃO MOSTRA NA RESPOSTA DA API
//    private String phoneNumber;

//    @JsonFormat(pattern = "dd/MM/yyyy")
//    private Date birthday;

    private String address;

//    @JsonIgnore // API NÃO DEVOLVE MAIS ESSE ATRIBUTO
//    @JsonSerialize(using = GenderSerializer.class) // USA A CLASSE GenderSerializer que criei. Serve apenas para devolver M ou F ao inves de "Male" ou "Female"
    private String gender;


    public PersonDTO() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(getId(), personDTO.getId()) && Objects.equals(getFirstName(), personDTO.getFirstName()) && Objects.equals(getLastName(), personDTO.getLastName()) && Objects.equals(getAddress(), personDTO.getAddress()) && Objects.equals(getGender(), personDTO.getGender());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getAddress(), getGender());
    }
}
