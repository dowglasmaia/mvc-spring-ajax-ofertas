$(document).ready(function () {

    $("#table-server").DataTable( {
        processing: true,
        serverSide: true,
        responsive: true,
        lengthMenu:[10,15,20,25],
            ajax:{
                url:"/promocao/datatables/server", 
                data: "data"
            },
            columns:[
                {data: 'id'},
                {data: 'titulo'},
                {data: 'site'},
                {data: 'linkPromocao'},
                {data: 'descricao'},
                {data: 'linkImage'},
                {data: 'preco'},
                {data: 'likes'},
                {data: 'dtaCadastroDateTime'},
                {data: 'categoria.titulo'},
               
            ]
    });

});

/*
<th scope="col">#</th>
<th scope="col">Titulo</th>
<th scope="col">Site</th>
<th scope="col">Link Promoção</th>
<th scope="col">Descrição</th>
<th scope="col">Link Imagem</th>
<th scope="col">Preço</th>
<th scope="col">Likes</th>
<th scope="col">Data de Cadastro</th>
<th scope="col">Categoria</th>
*/