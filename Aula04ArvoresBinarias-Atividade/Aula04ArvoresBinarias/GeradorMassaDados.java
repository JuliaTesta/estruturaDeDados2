import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.LinkedHashSet;
import java.util.Set;

public class GeradorMassaDados {

    public static void main(String[] args) {
        String arquivoSaida = "dispositivos.csv";
        int totalDispositivos = 50000;
        
        String[] nomes = {"SensorTemp", "SensorRuido", "SensorUmidade", "SensorCO2", "SensorPressao"};
        String[] locais = {"Setor_A", "Setor_B", "Laboratorio", "Armazem", "Usina", "Escritorio"};
        String[] unidades = {"°C", "dB", "%", "ppm", "hPa"};
        
        Random random = new Random();
        // Usamos um Set para garantir que os 50 mil IDs sejam únicos
        Set<Integer> idsUnicos = new LinkedHashSet<>();

        System.out.println("Gerando IDs únicos...");
        while (idsUnicos.size() < totalDispositivos) {
            // Gerando um intervalo grande o suficiente para não faltar número (1 a 1.000.000)
            idsUnicos.add(random.nextInt(1000000) + 1);
        }

        System.out.println("Gravando arquivo...");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoSaida))) {
            for (Integer id : idsUnicos) {
                int tipoIndex = random.nextInt(nomes.length);
                String nome = nomes[tipoIndex];
                String local = locais[random.nextInt(locais.length)];
                String unidade = unidades[tipoIndex];
                int alerta = 20 + random.nextInt(80);

                // Escreve: ID,Nome,Local,Unidade,ValorAlerta
                writer.write(id + "," + nome + "," + local + "," + unidade + "," + alerta);
                writer.newLine();
            }
            System.out.println("Sucesso! Arquivo '" + arquivoSaida + "' gerado com 50.000 dispositivos.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
}