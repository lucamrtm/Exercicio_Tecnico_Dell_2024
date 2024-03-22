# **Controle de Apostas - Sistema de Controle de Apostas**
## Este é um sistema desenvolvido em Java para controle de apostas, como parte de um exercício técnico em um processo seletivo de estágio. O sistema permite aos usuários registrar apostas, realizar sorteios e apurar os ganhadores, além de calcular e premiar os vencedores.
---
# Tecnologias Utilizadas
Java 17
---
# Funcionalidades
## O sistema possui as seguintes funcionalidades:

1. **Registro de Apostas:** Os usuários podem registrar suas apostas, inserindo as informações necessárias.

2. **Sorteio e Apuração:** O sistema realiza sorteios e apura os ganhadores com base nas cartelas de aposta registradas.

3. **Premiação:** Os vencedores são premiados com base no prêmio bruto anunciado, com tributação aplicada.

4. **Parcelamento do Prêmio:** Os ganhadores têm a opção de receber o prêmio de forma parcelada ao longo de anos, com juros aplicados para evitar a desvalorização.
---
# Organização do Código
## O código-fonte está organizado em classes, com cada funcionalidade agrupada em um ambiente específico. Por exemplo:

* **Financeiro:** Contém os cálculos relacionados à premiação e tributação.
* **Apostador:** Classe responsável por gerenciar informações dos apostadores.
* **CartelaDeAposta:** Classe que representa as cartelas de aposta e suas funcionalidades.
* **GerenciadorSistemaDeApostas:** Gerencia o sistema como um todo, incluindo o registro de apostas e a realização de sorteios.
* **Menu:** Classe responsável pela interação com o usuário, exibindo opções e recebendo entradas.
* **ValidadorEntradaUsuario:** Contém métodos para validar as entradas do usuário, garantindo que sejam válidas.
* **ResultadoApostas:** Representa o resultado das apostas, incluindo a lista de apostadores, o scanner e o gerenciador do sistema.
---
# Como Usar
## Para utilizar o sistema, siga as instruções abaixo:

1. Clone o repositório em sua máquina local.
2. Compile e execute o programa em um ambiente Java compatível.
3. Siga as instruções apresentadas no terminal para registrar as apostas, realizar sorteios e apurar os ganhadores.