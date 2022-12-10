package models;

import java.sql.SQLException;

import database.Database;

public class Tabung extends Database {
    // Constructor untuk Connect ke Database
    public Tabung() throws ClassNotFoundException, SQLException {
        super();
    }

    // Create
    public void insert(int pemasukan, int pengeluaran, String ket) throws SQLException {
        String sql = String.format("INSERT INTO nabung (pemasukan, pengeluaran, keterangan) VALUE (%d, %d, '%s')", pemasukan, pengeluaran,
                ket);
        this.setQuery(sql);
        this.execute();
    }

    // Read
    public void getAll() throws SQLException {
        String sql = "SELECT * FROM nabung";
        this.setQuery(sql);
        this.take();
    }
    
    // Update
    public void update(int id, int pemasukan, int pengeluaran, String ket) throws SQLException {
        String sql = String.format("UPDATE nabung SET pemasukan = %d, pengeluaran = %d, harga = '%s' WHERE id = %d",
                pemasukan, pengeluaran, ket, id);
        this.setQuery(sql);
        this.execute();
    }

    // Delete
    public void delete(int id) throws SQLException {
        String sql = String.format("DELETE FROM nabung WHERE id = %d", id);
        this.setQuery(sql);
        this.execute();
    }

    public String[][] show() throws SQLException {
        String[][] data = new String[this.len()][4];
        getAll();
        this.take();
        int i = 0;
        while (this.value.next()) {
            data[i][0] = Integer.toString(this.value.getInt("id"));
            data[i][1] = Integer.toString(this.value.getInt("pemasukan"));
            data[i][2] = Integer.toString(this.value.getInt("pengeluaran"));
            data[i][3] = this.value.getString("keterangan");
            i++;
        }
        return data;
    }
    
    public int len() throws SQLException {
        getAll();
        this.take();
        int i = 0;
        while (this.value.next()) {
            i++;
        }
        return i;
    }
}
