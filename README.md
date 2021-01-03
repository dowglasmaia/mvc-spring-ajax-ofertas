mvc-spring-ajax-ofertas

* https://cards-dev.twitter.com/validator
* https://developer.twitter.com/en
* https://developer.twitter.com/en/docs/twitter-for-websites/cards/guides/getting-started
* https://ogp.me/

* https://api.jquery.com/Jquery.ajax/
* https://www.w3schools.com/jquery/ajax_ajax.asp
* https://api.jquery.com/scrollTop/
* https://api.jquery.com/scroll/
* https://jqueryui.com/autocomplete/


* https://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/spring-model-attribute-method.html
* https://www.baeldung.com/spring-mvc-and-the-modelattribute-annotation


## #
Regras de Validação
Para criar as regras de validação back-end do formulário de cadastro de promoções vamos trabalhar com a especificação Bean Validation. Este recurso usa anotações sobre os atributos da classe e cada anotação tem um objetivo especifico dentro da validação, vejamos algumas dessas regras:

@NotNull - usada para campos que não são do tipo texto, como valor monetário, datas, enums e objetos complexos;

@NotEmpty - o campo não pode ser nulo nem estar vazio, os tipos avaliados são String, Collection, Map e Array.

@NotBlank - o campo deve conter pelo menos um caractere para passar na validação. Um espaço em branco não é tido como um caractere;

@Size - avalia o tamanho máximo e mínimo de um campo texto.;

@Positive - o campo deve ter um valor positivo para tipo Integer, Float, BigDecimal, Long, Double, etc.

* https://beanvalidation.org/2.0/

