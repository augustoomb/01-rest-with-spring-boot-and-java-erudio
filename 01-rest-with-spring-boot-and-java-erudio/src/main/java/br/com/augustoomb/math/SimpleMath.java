package br.com.augustoomb.math;

import br.com.augustoomb.exception.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;

public class SimpleMath {

    public Double sum(Double numberOne, Double numberTwo) {

        return numberOne + numberTwo;
    }

    public Double subtraction(Double numberOne, Double numberTwo) {

        return numberOne - numberTwo;
    }
}
