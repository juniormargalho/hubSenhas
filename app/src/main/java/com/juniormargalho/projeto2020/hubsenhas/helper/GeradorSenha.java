package com.juniormargalho.projeto2020.hubsenhas.helper;

import org.apache.commons.lang.RandomStringUtils;

public class GeradorSenha {
    private static String senha = "";

    public static String getSenha(String complexidade){

        switch (complexidade){
            case "1" :
                senha = getTwoNumeric() + getTwoNumeric() + getTwoNumeric();
                break;
            case "2" :
                senha = getTwoLowCase() + getTwoNumeric() + getTwoNumeric();
                break;
            case "3" :
                senha = getTwoUpCase() + getTwoNumeric() + getTwoNumeric() + getTwoLowCase();
                break;
            case "4" :
                senha = getTwoUpCase() + getTwoLowCase() + getTwoNumeric() + getTwoNumeric() + getTwoNumeric();
                break;
            case "5" :
                senha = getTwoLowCase() + getTwoNumeric() + getTwoNumeric() + getTwoNumeric() + getTwoUpCase() + getTwoEspecial();
                break;
        }
        return senha;
    }

    private static String getTwoLowCase(){
        String twoLowCase = "";
        twoLowCase = RandomStringUtils.random(2, 'a', 'z',true,false);
        return twoLowCase;
    }

    private static String getTwoUpCase(){
        String twoUpCase = "";
        twoUpCase = RandomStringUtils.random(2, 'A', 'Z',true,false);
        return twoUpCase;
    }

    private static String getTwoNumeric(){
        String twoNumeric = "";
        twoNumeric = RandomStringUtils.randomNumeric(2);
        return twoNumeric;
    }

    private static String getTwoEspecial(){
        String twoEspecial = "";
        char[] cacteresEspeciais = {'@', '#', '$', '%'};
        twoEspecial = RandomStringUtils.random(2, cacteresEspeciais);
        return twoEspecial;
    }
}
