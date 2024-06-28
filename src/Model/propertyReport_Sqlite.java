package Model;

import java.sql.*;

import gui.util.Directory;

public class propertyReport_Sqlite {
    // Método para estabelecer a conexão com o banco de dados SQLite
    private static Connection connect() {

        String directoryPath = "/home/daniel/IdeaProjects/Calculadora/db";
        String dbFileName = "Calculate.db";

        Directory.directory(directoryPath);

        // String de conexão para o SQLite
        String url = "jdbc:sqlite:" + directoryPath + "/" + dbFileName;

        Connection conn = null;
        try {
            // Estabelece a conexão com o banco de dados
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Método para criar a tabela 'users' no banco de dados SQLite, se ela não existir
    public static void createTable() {
        // Declaração SQL para criar a tabela 'users'
        String sql = "CREATE TABLE IF NOT EXISTS calculate (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " calculations TEXT NOT NULL,\n"
                + " formulation TEXT NOT NULL UNIQUE\n"
                + ");";

        try (Connection conn = connect(); // Estabelece a conexão com o banco de dados
             Statement stmt = conn.createStatement()) {
            // Executa a declaração SQL para criar a tabela
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para inserir um novo registro na tabela 'users'
    public static void insert(String calculations, String formulation) {
        String sql = "INSERT INTO calculate(calculations,formulation) VALUES(?,?)";

        try (Connection conn = connect(); // Estabelece a conexão com o banco de dados
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Define os parâmetros da declaração SQL (calculations e formulation)
            pstmt.setString(1, calculations);
            pstmt.setString(2, formulation);
            // Executa a inserção do registro
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para atualizar um registro na tabela 'calculate' com base no ID
    public static void update(int id, String calculations, String formulation) {
        String sql = "UPDATE calculate SET calculations = ? , "
                + "formulation = ? "
                + "WHERE id = ?";

        try (Connection conn = connect(); // Estabelece a conexão com o banco de dados
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Define os parâmetros da declaração SQL (nome, email e ID)
            pstmt.setString(1, calculations);
            pstmt.setString(2, formulation);
            pstmt.setInt(3, id);
            // Executa a atualização do registro
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para deletar um registro na tabela 'users' com base no ID
    public static void delete(int id) {
        String sql = "DELETE FROM calculate WHERE id = ?";

        try (Connection conn = connect(); // Estabelece a conexão com o banco de dados
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Define o parâmetro da declaração SQL (ID)
            pstmt.setInt(1, id);
            // Executa a exclusão do registro
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para selecionar e exibir todos os registros da tabela 'users'
    public static void selectAll() {
        String sql = "SELECT id, calculations, formulation FROM calculate";

        try (Connection conn = connect(); // Estabelece a conexão com o banco de dados
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // Itera através do conjunto de resultados e exibe cada registro
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t"
                        + rs.getString("calculations") + "\t"
                        + rs.getString("formulation"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}

