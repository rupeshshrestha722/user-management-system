SET SQL_SAFE_UPDATES = 0;
DELETE FROM `user`;
insert  into `user`(`id`,`email`,`first_name`,`last_name`,`password`,`username`, `date_of_birth`)
values (1,'rupesh@gmail.com','Rupesh','Shrestha','$2y$10$Ksf4mVvSHraszPdDxhXhJuibNj6Q6v/ou01CH0IZMZl5CtaTmLnvC','rupesh', '1994-03-19');
SET SQL_SAFE_UPDATES = 1;
