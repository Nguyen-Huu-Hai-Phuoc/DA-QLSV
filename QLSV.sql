create database QLSV_PRO1041
go
use QLSV_PRO1041
go

create table Khoa
(
	MaKHoa varchar(10) primary key,
	TenKhoa nvarchar(100) not null,
	NamThanhLap int not null,
)
go

create table GiangVien
(
	MaGV varchar(10) primary key,
	TenGV nvarchar(50) not null,
	GioiTinh bit not null,
	SDT varchar(15) not null,
	Email varchar(30) not null,
	Hinh nvarchar(100) not null
)
go

create table Lop
(
	MaKhoa varchar(10) not null,
	MaLop varchar(10) primary key,	
	TenLop varchar(30) not null,

	foreign key(MaKhoa) references Khoa(MaKhoa),
)
go

create table MonHoc
(
	MaMH varchar(10) primary key,
	TenMH nvarchar(100),
	MaGV varchar(10) not null,
	HocKi int not null,
	MaKHoa varchar(10) not null,

	foreign key(MaGV) references GiangVien(MaGV),	
	foreign key(MaKhoa) references Khoa(MaKhoa),	
)
go

create table SinhVien
(
	MaSV varchar(10) primary key,
	HoTen nvarchar(100) not null,
	NgaySinh date not null,
	GioiTinh bit not null,
	DiaChi nvarchar(100) not null,
	MaLop varchar(10) not null,
	Hinh nvarchar(50) not null,
	Email varchar(20) not null

	foreign key(MaLop) references Lop(MaLop)
)
go

create table KetQua
(
	MaSV varchar(10),
	HoTen nvarchar(100),
	MaLop varchar(10) not null,
	MaMH varchar(10) not null,
	HocKi int,
	DiemThiLan1 float,
	DiemThiLan2 float,
	DiemTB float not null,
	XepLoai nvarchar(30) not null,
	GhiChu nvarchar(50) 

	primary key(MaSV, MaLop, MaMH),
	foreign key(MaSV) references SinhVien(MaSV),
	foreign key(MaMH) references MonHoc(MaMH), 
	foreign key(MaLop) references Lop(MaLop) 
)
go

create table TaiKhoan
(
	TenDangNhap nvarchar(20) primary key,
	MatKhau nvarchar(30) not null,
	VaiTro nvarchar(30) not null,
	Email nvarchar(30) not null
)
go

create table DangKyThiLai
(
	STT int Identity(1,1) primary key,
	MaSV nvarchar(10) not null,
	HoTen nvarchar(100) not null,
	MaLop varchar(10) not null, 
	MaMH varchar(10) not null
)
go
-- Nhap du lieu cho bang Khoa
Insert into Khoa values('CNTT', 'Cong Nghe Thong Tin', 1890)
go
Insert into Khoa values('KT', 'Kien Truc', 1895)
go
Insert into Khoa values('LS', 'Luat Su', 1890)
go
Insert into Khoa values('QTKD', 'Quan Tri Kinh Doanh', 1890)
go
Insert into Khoa values('KTT', 'Ke Toan', 1895)
go

-- Nhap du lieu cho bang GiangVien
Insert into GiangVien values('GV001', 'Le Anh Tu', 1,'0375265891','tula@gmail.com','')
go
Insert into GiangVien values('GV002', 'Le Van Phung', 1,'0374668695','phunglv@gmail.com','')
go
Insert into GiangVien values('GV003', 'Cao Hoang Phuc', 1,'0373455821','phucch@gmail.com','')
go
Insert into GiangVien values('GV004', 'Nguyen Thi Huong', 0,'0375345823','huongnt@gmail.com','')
go
Insert into GiangVien values('GV005', 'Nguyen Thi Hong Nga', 0,'0375875845','nganth@gmail.com','')
go

-- Nhap du lieu cho bang Lop
Insert into Lop values('CNTT', 'UD16315', 'Ung Dung Phan Mem')
go
Insert into Lop values('KT', 'NT26316', 'Thiet Ke Noi That')
go
Insert into Lop values('KTT', 'KTT36317', 'Ke Toan Kinh Te')
go
Insert into Lop values('QLKD', 'QL46318', 'Quan Li Nha Hang - Khac San')
go
Insert into Lop values('LS', 'LS56319', 'Luat Kinh Te')
go

-- Nhap du lieu cho bang MonHoc
Insert into MonHoc values('CSDL', 'Co So Du Lieu', 'GV001',1,'CNTT')
go
Insert into MonHoc values('JAVA', 'Lap Trinh Java Co Ban', 'GV001',1,'CNTT')
go
Insert into MonHoc values('TCRR', 'Toan Roi Rac', 'GV002',1,'KTT')
go
Insert into MonHoc values('TCC', 'Toan Cao Cap', 'GV002',1,'KTT')
go

-- Nhap du lieu cho bang SinhVien
Insert into SinhVien values('PS15783', 'Tran Quoc Thang','2002-07-09',1,'An Giang','UD16315','','thang@gmail.com')
go
Insert into SinhVien values('PS12233', 'Tran Phuc Hau','2002-04-06',1,'Vinh Long','UD16315','','hau@gmail.com')
go
Insert into SinhVien values('PS15631', 'Huynh Huu Vinh','2002-03-01',1,'Da Nang','UD16315','','vinh@gmail.com')
go
Insert into SinhVien values('PS11243', 'Hoang Manh Dung','2002-02-02',1,'Hai Phong','UD16315','','dung@gmail.com')
go
Insert into SinhVien values('PS12453', 'Nguyen Huu Hai Phuoc','2002-01-07',1,'Long An','UD16315','','phuoc@gmail.com')
go

-- Nhap du lieu cho bang KetQua
Insert into KetQua values('PS15783', 'Tran Quoc Thang','UD16315','JAVA',1,5.5,'', 6.0,'Trung Binh','')
go
Insert into KetQua values('PS12233', 'Tran Phuc Hau','UD16315','CSDL',1,8.0,'', 8.5,'Gioi','')
go
Insert into KetQua values('PS15631', 'Huynh Huu Vinh','UD16315','JAVA',1,10.0,'', 10.0,'Gioi','')
go
Insert into KetQua values('PS11243', 'Hoang Manh Dung','UD16315','CSDL',1,1.0,1.5,2.5,'Yeu','Thi Lai')
go
Insert into KetQua values('PS12453', 'Nguyen Huu Hai Phuoc','UD16315','JAVA',1,9.0,'', 9.5,'Gioi','')
go

-- Nhap du lieu cho bang TaiKhoan
Insert into TaiKhoan values('TeoNV', '123','Can Bo Dao Tao','teo@gmail.com')
go
Insert into TaiKhoan values('PS15783', '123','Sinh Vien','thang@gmail.com')
go
Insert into TaiKhoan values('GV01', '123','Giang Vien','tula@gmail.com')
go
	 