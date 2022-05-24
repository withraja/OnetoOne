Tugas ini dibuat untuk SYNRGY Academy Batch 4
BackEnd Developer JAVA

=============================================

Saya membuat dua buah class Mobil dan MobilDetail sehingga membentuk dua table di database dengan keterangan sebagai berikut:

Table Mobil memiliki field:
1. id (Long)
2. brand (String)
3. mobil_detail_id (Long)
4. is_deleted (boolean)


Table MobilDetail memiliki field:
1. id (Long)
2. color (String)
3. isNew (Boolean)
4. year (Integer)
5. price (Double)

Relasi antara table Mobil dan MobilDetail adalah One-to-One dan hanya table Mobil yang bisa mengakses table MobilDetail, tapi tidak sebaliknya. 
Tabel ini dibuat di PostgreSQL menggunakan SpringBoot Framework

=============================================

Lalu saya membuat API untuk:
1. Insert 4 data secara random ke table Mobil dan MobilDetail
2. GetAll Mobil dan MobilDetail yang isDelete = false
3. Get Mobil dan MobilDetail berdasarkan Mobil id yang isDelete = false
4. Update mobilDetail berdasarkan Mobil id
5. Soft delete Mobil dengan mengupdate isDelete = true