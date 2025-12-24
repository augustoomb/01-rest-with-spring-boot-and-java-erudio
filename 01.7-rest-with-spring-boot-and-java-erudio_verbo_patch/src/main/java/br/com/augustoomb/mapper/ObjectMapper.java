package br.com.augustoomb.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class ObjectMapper {

    // Ser estática significa que existe apenas uma única instância desse mapeador na memória para toda a aplicação.
    // É a forma padrão de inicializar o Dozer, configurando-o com suas regras automáticas e default.
    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault(); // VAI MAPEAR ENTIDADE PARA DTO E DTO PARA ENTIDADE

    // O metodo eh público e estático, permitindo que ele seja chamado diretamente pela classe, sem a necessidade de instanciar
    public static <O, D> D parseObject(O origin, Class<D> destination) {

        // Cria uma nova instância do tipo D.
        // Copia as propriedades de origin para essa nova instância, onde os nomes dos campos coincidirem.
        // Retorna o novo objeto do tipo D.
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        List<D> destinationObjects = new ArrayList<D>();

        for (Object o: origin) {
            destinationObjects.add(mapper.map(o, destination));
        }

        return destinationObjects;
    }
}


// public static <O, D> D parseObject(O origin, Class<D> destination)
/*
    <O, D> // são os tipos genéricos

    D // tipo de retorno

    parseObject // nome do metodo
 */