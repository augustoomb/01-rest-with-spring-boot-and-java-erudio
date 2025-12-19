package br.com.augustoomb.serialization.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

// PARA ENTREGAR RESPOSTA AO USUÁRIO EM FORMATO yaml
public final class YamlJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {
    protected YamlJackson2HttpMessageConverter() {
        super(new YAMLMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL), MediaType.parseMediaType("application/yaml"));
    }
}
