# Variáveis de Compilação
JAVAC = javac
JAVA = java
SRC_DIR = src
BIN_DIR = bin
DOC_DIR = docs

# Define a classe principal (pacote.Classe)
MAIN_CLASS = barbershop.Main

# Encontra todos os arquivos .java dentro de src/ (recursivamente)
SOURCES := $(shell find $(SRC_DIR) -name "*.java")

# Regra padrão (apenas digitar 'make')
all: compile

# Cria o diretório bin e compila os arquivos
compile:
	@echo "Criando diretorio bin..."
	@mkdir -p $(BIN_DIR)
	@echo "Compilando fontes..."
	@$(JAVAC) -d $(BIN_DIR) -sourcepath $(SRC_DIR) $(SOURCES)
	@echo "Compilacao concluida com sucesso!"

# Executa o programa
run: compile
	@echo "Executando Barbershop Manager..."
	@echo "--------------------------------"
	@$(JAVA) -cp $(BIN_DIR) $(MAIN_CLASS)

# Limpa os arquivos compilados (.class)
clean:
	@echo "Limpando arquivos compilados..."
	@rm -rf $(BIN_DIR)
	@echo "Limpeza concluida."

# (Opcional) Limpa também os dados CSV gerados
clean-data:
	@echo "Removendo pasta de dados persistidos..."
	@rm -rf dados
	@echo "Dados removidos."

.PHONY: all compile run clean clean-data