-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 07-Jul-2021 às 21:55
-- Versão do servidor: 5.7.25
-- versão do PHP: 7.1.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbestoque`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `categoria`
--

CREATE TABLE `categoria` (
  `codCategoria` int(11) NOT NULL,
  `nome` varchar(15) NOT NULL,
  `descricao` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `categoria`
--

INSERT INTO `categoria` (`codCategoria`, `nome`, `descricao`) VALUES
(1, 'Alimento', 'Produtos comestível'),
(3, 'Estética', 'Beleza do corpo'),
(4, 'Academia', 'Cuidar da saúde'),
(5, 'Game', 'Jogos para diverção'),
(8, 'Tiburcinho', 'lalala');

-- --------------------------------------------------------

--
-- Estrutura da tabela `entrada`
--

CREATE TABLE `entrada` (
  `codEntrada` int(11) NOT NULL,
  `codProduto` int(11) NOT NULL,
  `qtdPedido` int(11) NOT NULL,
  `dataPedido` varchar(10) NOT NULL,
  `userPedido` int(13) NOT NULL,
  `codFornecedor` int(11) NOT NULL,
  `dataEntrega` varchar(10) DEFAULT NULL,
  `userEntrada` int(13) DEFAULT NULL,
  `qtdEntrega` int(11) DEFAULT NULL,
  `caminhoNF` varchar(500) DEFAULT NULL,
  `situacao` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `entrada`
--

INSERT INTO `entrada` (`codEntrada`, `codProduto`, `qtdPedido`, `dataPedido`, `userPedido`, `codFornecedor`, `dataEntrega`, `userEntrada`, `qtdEntrega`, `caminhoNF`, `situacao`) VALUES
(1, 1, 50, '20/05/2021', 2, 1, '02-06-2019', 2, 50, 'C:/Users/LucasLima/Desktop/NFs/Produto 1_02-07-2021.pdf', 'entregue'),
(2, 2, 30, '29/06/2020', 2, 1, '25-08-2020', 2, 30, 'C:/Users/LucasLima/Desktop/NFs/Produto 2_02-07-2021.pdf', 'entregue'),
(3, 1, 90, '02/07/2021', 2, 1, '03-07-2021', 1, 90, 'C:/Users/LucasLima/Desktop/NFs/Produto 1_03-07-2021.pdf', 'entregue'),
(4, 2, 10, '02/07/2021', 2, 1, '02-07-2021', 2, 0, '', 'cancelado'),
(5, 2, 95, '02/07/2021', 2, 1, '03-07-2021', 1, 95, 'C:/Users/LucasLima/Desktop/NFs/Produto 2_03-07-2021.pdf', 'entregue'),
(6, 1, 5, '01/04/2021', 2, 1, '11-04-2021', 2, 0, '', 'cancelado'),
(7, 3, 30, '03/07/2021', 1, 1, NULL, NULL, NULL, NULL, 'pedido'),
(8, 2, 10, '05/07/2021', 1, 4, '05-07-2021', 1, 10, 'C:/Users/LucasLima/Documents/NetBeansProjects/ControleDeEstoque/src/ArquivosNF/Produto 2_05-07-2021.pdf', 'entregue'),
(9, 1, 50, '05/07/2021', 1, 1, '05-07-2021', 1, 50, 'C:/Users/LucasLima/Documents/NetBeansProjects/ControleDeEstoque/src/ArquivosNF/Produto 1_05-07-2021.pdf', 'entregue'),
(10, 1, 20, '05/07/2021', 1, 1, '05-07-2021', 1, 20, 'C:/Users/LucasLima/Desktop/NFs/Produto 1_05-07-2021.pdf', 'entregue'),
(11, 2, 40, '05/07/2021', 1, 3, NULL, NULL, NULL, NULL, 'pedido');

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedor`
--

CREATE TABLE `fornecedor` (
  `codFornecedor` int(11) NOT NULL,
  `cnpj` varchar(25) NOT NULL,
  `tipoPessoa` varchar(50) NOT NULL,
  `razaoSocial` varchar(100) NOT NULL,
  `nomeFantasia` varchar(100) NOT NULL,
  `dataCadastro` varchar(10) NOT NULL,
  `endereco` varchar(150) NOT NULL,
  `bairro` varchar(100) DEFAULT NULL,
  `cidade` varchar(100) NOT NULL,
  `uf` varchar(2) NOT NULL,
  `celular` varchar(14) DEFAULT NULL,
  `telefone` varchar(16) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `fornecedor`
--

INSERT INTO `fornecedor` (`codFornecedor`, `cnpj`, `tipoPessoa`, `razaoSocial`, `nomeFantasia`, `dataCadastro`, `endereco`, `bairro`, `cidade`, `uf`, `celular`, `telefone`, `email`) VALUES
(1, '11.111.111/1111-11', 'Pessoa', 'Minha empresa ME Ltda', 'Minha empresa', '11/11/1111', 'fasdjhflhaslkdhj', 'fldakjshfdksa', 'dfslkjafhlksadhj', 'UF', '(11)11111-1111', '(11) 1111-1111', 'afsdhgfkajsdfka'),
(2, '11.111.111/1111-11', 'Jurídica', 'fadsafsa', 'fsadfasd', '22/22/2222', 'TesteDeAtualizacao', 'fsad', 'fasd', 'fd', '(44)44444-4444', '(12) 3123-1123', 'meu@email'),
(3, '11.111.111/1111-11', 'Tipo de Pessoa', 'outra empresa', 'doidos e Cia', '11/11/1111', 'Endereco alterado', 'fldakjshfdksa', 'dfslkjafhlksadhj', 'UF', '(11)11111-1111', '(  )     -    ', 'afsdhgfkajsdfka'),
(4, '12.872.065/0001-06', 'Física', 'Igor boladão', 'turma do Igão', '03/10/2020', 'Rua FAITEC teste', 'FAI', 'TEC', 'FI', '(03)03030-3003', '(02) 0202-0202', 'opicional');

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `codUsuario` int(11) NOT NULL,
  `nome` varchar(150) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `dataNascimento` varchar(10) NOT NULL,
  `sexo` varchar(9) NOT NULL,
  `endereco` varchar(150) DEFAULT NULL,
  `bairro` varchar(100) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `uf` varchar(20) NOT NULL,
  `celular` varchar(14) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `cargo` varchar(15) NOT NULL,
  `dataContratacao` varchar(10) NOT NULL,
  `senha` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `funcionario`
--

INSERT INTO `funcionario` (`codUsuario`, `nome`, `cpf`, `dataNascimento`, `sexo`, `endereco`, `bairro`, `cidade`, `uf`, `celular`, `email`, `cargo`, `dataContratacao`, `senha`) VALUES
(1, 'Facilita', '000.000.000-00', '00/00/0000', 'Feminino', 'R.Facilita Minha Vida', 'facilita', 'facilita', 'FC', '(00)00000-0000', 'facilita', 'Administrador', '05/09/2020', 'fac$4146'),
(2, 'Lucas Lima', '010.101.010-10', '11/11/1111', 'Masculino', 'Meu_Endereco', 'Bairro', 'Cidade', 'UF', '(00)00000-0000', 'lucas@lima.com', 'Gerente', '22/02/2020', '1234'),
(3, 'Hermane Boavida', '111.111.111-11', '11/11/1111', 'Masculino', 'dfhadhf', 'dahsd', 'dlakjsdh', 'DF', '(11)11111-1111', 'fadfasdfas', 'Gerente', '11/11/1111', '4567'),
(4, 'Funcionario 1', '621.486.340-46', '01/02/2020', 'Masculino', 'Rua Delegado Manoel Pereira Pinto', 'Quilombo', 'Conceição dos Ouros', 'MG', '(35)99213-6012', 'lucassouzalima1478@outlook.com', 'Funcionario', '03/10/2020', 'teste1234'),
(5, 'Funcionario 2', '442.887.980-60', '01/03/2020', 'Feminino', 'Qualquer Endereço', 'FAI', 'FAITEC', 'FI', '(88)88888-8888', 'novoEmailCadastrado', 'Cozinha', '01/06/2020', 'teste1478'),
(6, 'Funcionario 3', '132.149.843-21', '12/16/5432', 'Masculino', 'agasdfffasf', 'asdfasdf', 'dfasd', 'fa', '(16)54324-3245', 'fasdfa', 'Funcionario', '21/10/2020', '123');

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE `produto` (
  `codProduto` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `descricao` varchar(150) NOT NULL,
  `codCategoria` int(20) NOT NULL,
  `qtdEstoque` int(11) NOT NULL,
  `preco` varchar(10) NOT NULL,
  `qtdMinima` int(11) NOT NULL,
  `codFornecedor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`codProduto`, `nome`, `descricao`, `codCategoria`, `qtdEstoque`, `preco`, `qtdMinima`, `codFornecedor`) VALUES
(1, 'Produto 1', 'teste de cadastro de produto', 1, 6, '3,50', 10, 1),
(2, 'Produto 2', 'alterado', 5, 72, '6,25', 10, 3),
(3, 'Silvana', 'Melhor professora', 4, 190, '50,00', 60, 3),
(5, 'Tiburcinho', 'fasdfasdfasd', 1, 20, '120,00', 2020, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `saida`
--

CREATE TABLE `saida` (
  `codSaida` int(11) NOT NULL,
  `data` varchar(10) NOT NULL,
  `qtdProduto` int(11) NOT NULL,
  `codProduto` int(11) NOT NULL,
  `codUsuario` int(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `saida`
--

INSERT INTO `saida` (`codSaida`, `data`, `qtdProduto`, `codProduto`, `codUsuario`) VALUES
(1, '03-03-2020', 50, 1, 1),
(2, '03-07-2021', 30, 2, 2),
(3, '04-07-2020', 10, 1, 2),
(4, '06-03-2019', 55, 2, 3),
(5, '29-08-2019', 20, 1, 1),
(6, '03-07-2021', 10, 3, 1),
(7, '03-07-2021', 30, 2, 1),
(8, '', 40, 1, 1),
(9, '03-07-2021', 40, 1, 1),
(10, '03-07-2021', 20, 2, 1),
(11, '03-07-2021', 39, 2, 1),
(12, '03-07-2021', 79, 1, 1),
(13, '05-07-2021', 9, 2, 1),
(14, '05-07-2021', 70, 1, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`codCategoria`);

--
-- Indexes for table `entrada`
--
ALTER TABLE `entrada`
  ADD PRIMARY KEY (`codEntrada`),
  ADD KEY `fk_prod_entrada` (`codProduto`),
  ADD KEY `fk_forn_entrada` (`codFornecedor`),
  ADD KEY `fk_usuario_entrada` (`userEntrada`),
  ADD KEY `fk_usuario_pedido` (`userPedido`);

--
-- Indexes for table `fornecedor`
--
ALTER TABLE `fornecedor`
  ADD PRIMARY KEY (`codFornecedor`) USING BTREE;

--
-- Indexes for table `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`codUsuario`);

--
-- Indexes for table `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`codProduto`),
  ADD KEY `fk_forn_Produto` (`codFornecedor`),
  ADD KEY `fk_cate_Produto` (`codCategoria`);

--
-- Indexes for table `saida`
--
ALTER TABLE `saida`
  ADD PRIMARY KEY (`codSaida`),
  ADD KEY `fk_prod_saida` (`codProduto`),
  ADD KEY `fk_usuario_saida` (`codUsuario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categoria`
--
ALTER TABLE `categoria`
  MODIFY `codCategoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `entrada`
--
ALTER TABLE `entrada`
  MODIFY `codEntrada` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `fornecedor`
--
ALTER TABLE `fornecedor`
  MODIFY `codFornecedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `codUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `produto`
--
ALTER TABLE `produto`
  MODIFY `codProduto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `saida`
--
ALTER TABLE `saida`
  MODIFY `codSaida` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
