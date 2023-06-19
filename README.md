# GithubSearch

O app "GithubSearch" consome a API do GitHub para exibir informações de usuários e listagem de repositórios. O app possui três telas principais:
- Listagem de usuários - Tela onde é possível visualizar usuários de teste fornecidos pela API, bem como realizar a busca de usuário pelo nome.
- Favoritos - Tela onde é possível visualizar os usuários marcados como favoritos
- Eu - Tela onde é possível inserir o próprio usuário do github para que seja mais fácil consultar as próprias informações

Ao clicar em usuário na listagem, será aberta a tela de detalhe de usuário. Na tela de detalhe temos os seguintes elementos
- Foto do usuário
- Nome do usuário
- Username do usuário
- Botão de favoritar
- Biografia
- Quantidade de seguidores, quantas pessoas seguem, e quantidade de repositórios públicos
- Localização, empresa, link de blog pessoal, link do perfil do twitter e email
- Listagem dos repositórios públicos do usuário

Ao clicar em um link (blog ou twitter), será aberto o navegador com a URL. No caso do Twitter, caso o usuário tenha o app instalado, será aberto o app do Twitter. Ao clicar em um endereço de email, será aberto o cliente de email do usário
(caso disponível)

Na tela de listagem também é possível encontrar a listagem de repositórios públicos de um usuário. Ao clicar em um repositório, a página daquele repositório no GitHub será aberta no navegador e, caso o usuário possua o app do GitHub instalado, seŕá direcionado para o app.

Ainda na tela de listagem, há um botão de busca que permite inserir o nome de um usuário. Caso o usuário seja encontrado, será aberta a tela de detalhe desse usuário, caso contrário, será exibida uma mensagem de erro.

O comportamento descrito acima também se aplica à segunda tela, de favoritos, com a exceção da ausência do botão de busca.

Ao acessar a tela "Eu" pela primeira vez, será solicitado o seu usuário do Github. Ao inserir um usuário válido, esse usuário ficará salvo e a tela de detalhe desse usuário será exibida sempre que acessar a tela "Eu".

# Escolhas Técnicas
- O App utiliza a biblioteca Retrofit para a realização de requests para a API. Por já ser amplamente utilizada pela comunidade de desenvolvedores, possui amplo suporte, o que facilita o desenvolvimento (https://github.com/square/retrofit)
- Para a injeção de dependência é a utilizada a biblioteca Hilt, que atualmente é recomendada pela própria Google para essa finalidade (https://developer.android.com/training/dependency-injection#hilt)
- Para a navegação entre telas foi utilizada o Navigation, que é recomendado pela própria Google para essa finalidade. Em decorrência dessa escolha, e baseado na sugestão da documentação, o app possui a arquitetura de "Single Activity" (https://developer.android.com/guide/navigation)
- O carregamento de imagens está sendo realizado com a biblioteca "Glide", que é a biblioteca recomendada pelo Google para essa finalidade (https://developer.android.com/topic/performance/graphics/load-bitmap?hl=pt-br)
- Para salvar os dados de usuários favoritados, é utilizada o Room, a alternativa recomendada pela Google para o armazenamento de dados em um banco de dados local (https://developer.android.com/training/data-storage/room)
- O app possui um tema dinâmico do Material 3. Com isso, quando o app for executado em aparelhos compatíveis (Android 12+), as cores do app mudarão de acordo com o papel de parede do dispositivo. Para versões anteriores serão utilizadas as cores padrão do Material Design (https://m3.material.io/get-started)
- Ausência da paginação: como a API retorna os dados em uma listagem única, não foi implementada a paginação na listagem de usuários

# Ponto de atenção
A API do Github possui limitação de requisições por tempo. Sendo assim, ao fazer várias requisições em um curto espaço de tempo, o usuário poderá receber um feedback de erro. Caso isso ocorra, aguarde alguns minutos e tente novamente.
