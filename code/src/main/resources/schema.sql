-- Membuat tabel aritmatika (hanya jika belum ada). Hasil TIDAK disimpan, dihitung di halaman HTML.
CREATE TABLE IF NOT EXISTS aritmatika (
    id   BIGSERIAL PRIMARY KEY,
    bil1 DOUBLE PRECISION NOT NULL,
    opt  VARCHAR(1)       NOT NULL,
    bil2 DOUBLE PRECISION NOT NULL
);
