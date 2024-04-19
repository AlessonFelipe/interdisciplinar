//package com.example.interdisciplinar.models;
//
//import jakarta.persistence.*;
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.net.MalformedURLException;
//import java.net.URISyntaxException;
//import java.net.URL;
//import java.util.UUID;
//
//@Entity
//@Table(name="Enderecos")
//public class AddressModel implements Serializable {
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
//    private UUID idEndereco;
//
//    private String cep;
//    private String rua; // Alteração da palavra "logradouro" para "rua"
//    private String complemento;
//    private String bairro;
//    private String cidade;
//
//    // Getters e setters
//    public UUID getIdEndereco() {
//        return idEndereco;
//    }
//
//    public void setIdEndereco(UUID idEndereco) {
//        this.idEndereco = idEndereco;
//    }
//
//    public String getCep() {
//        return cep;
//    }
//
//    public void setCep(String cep) {
//        this.cep = cep;
//    }
//
//    public String getRua() {
//        return rua;
//    }
//
//    public void setRua(String rua) {
//        this.rua = rua;
//    }
//
//    public String getComplemento() {
//        return complemento;
//    }
//
//    public void setComplemento(String complemento) {
//        this.complemento = complemento;
//    }
//
//    public String getBairro() {
//        return bairro;
//    }
//
//    public void setBairro(String bairro) {
//        this.bairro = bairro;
//    }
//
//    public String getCidade() {
//        return cidade;
//    }
//
//    public void setCidade(String cidade) {
//        this.cidade = cidade;
//    }
//
//}

package com.example.interdisciplinar.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

@Entity
@Table(name = "Enderecos")
public class AddressModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idEndereco;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private UserModel user;

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

    // Construtor vazio necessário para JPA
    public AddressModel() {}

    // Construtor para criar um AddressModel com o CEP
    public AddressModel(String cep) {
        this.cep = cep;
        preencherEndereco();
    }

    // Getters e setters

    public UUID getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(UUID idEndereco) {
        this.idEndereco = idEndereco;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
        preencherEndereco();
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    private void preencherEndereco() {
        try {
            URL url = new URL("https://viacep.com.br/ws/" + cep + "/json/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(inputStream);

            if (rootNode.has("erro") && rootNode.get("erro").asBoolean()) {
                throw new IllegalArgumentException("CEP não encontrado.");
            }

            logradouro = rootNode.get("logradouro").asText();
            complemento = rootNode.get("complemento").asText();
            bairro = rootNode.get("bairro").asText();
            cidade = rootNode.get("localidade").asText();
            estado = rootNode.get("uf").asText();

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
