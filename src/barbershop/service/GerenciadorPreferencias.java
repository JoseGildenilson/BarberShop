package barbershop.service;

import java.io.*;
import java.util.Properties;

public class GerenciadorPreferencias {
    private static final String ARQUIVO_CONFIG = "config.properties";
    private Properties props;

    public GerenciadorPreferencias() {
        props = new Properties();
        carregarPreferencias();
    }

    private void carregarPreferencias() {
        File arquivo = new File(ARQUIVO_CONFIG);
        if (arquivo.exists()) {
            try (FileInputStream in = new FileInputStream(arquivo)) {
                props.load(in);
            } catch (IOException e) {
                System.out.println("Não foi possível carregar as preferências.");
            }
        } else {
            // Valores padrão se o arquivo não existir
            props.setProperty("nome_usuario", "Operador");
            props.setProperty("tema", "CLARO");
            salvarPreferencias();
        }
    }

    public void salvarPreferencias() {
        try (FileOutputStream out = new FileOutputStream(ARQUIVO_CONFIG)) {
            props.store(out, "Preferencias do BarberShop Manager");
        } catch (IOException e) {
            System.out.println("Erro ao salvar preferências.");
        }
    }

    // Getters e Setters
    public String getNomeUsuario() {
        return props.getProperty("nome_usuario", "Operador");
    }

    public void setNomeUsuario(String nome) {
        props.setProperty("nome_usuario", nome);
        salvarPreferencias();
    }

    public String getTema() {
        return props.getProperty("tema", "CLARO");
    }

    public void setTema(String tema) {
        props.setProperty("tema", tema);
        salvarPreferencias();
    }
}