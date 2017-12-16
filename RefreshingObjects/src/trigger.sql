use mojabaza;

create trigger calculate_tax
before insert on employee for each row
set new.tax = new.salary * 0.20;