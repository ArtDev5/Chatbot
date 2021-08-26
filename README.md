Chatbot no Messenger utilizando o Dialogflow como NLP e consumindo uma api de previsão de tempo

# Para fazer o Chatbot funcionar:

### Utilizar alguma ferramenta para expor o seu localhost para a web (recomendado NGROK).

### Configurar o Webhook do Facebook para o Messenger
  https://developers.facebook.com/docs/graph-api/webhooks/getting-started </br>
  https://developers.facebook.com/docs/messenger-platform/webhook

### Criar um agente no Dialogflow e suas respectivas intenções e entidades.
  https://cloud.google.com/dialogflow/es/docs/concepts?hl=pt-br </br>
  https://cloud.google.com/dialogflow/es/docs/quick/api

# Sobre a API de clima
Utilizei a API HG Weather da HG brasil (https://hgbrasil.com/status/weather). No módulo *climate* é onde está o contrato e a lógica para utilização dessa API, então caso deseje utilizar alguma diferente, é ali que deve ser mexido.

# Interações com o bot
Dentro do módulo *services* há o módulo *questions*, que é onde ficam as classes de cada interação com o bot, onde define o que ele espera e suas possíveis respostas.

# Geral
No *application.properties* é onde estão as variáveis de ambiente que são importantes para a utilização das ferramentas citadas acima.
