CREATE OR REPLACE FUNCTION public.orderonschedule(
	hepatime integer,
	normaltime integer)
    RETURNS SETOF order_schedules 
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
    ROWS 1000

AS $BODY$
BEGIN
  return query
	select * from order_schedules 
	where 
	status_order_schedule like 'đang chờ' 
	and delete_status = 1
	and
	(product_name like '%hepa%' 
	and 
	EXTRACT(DAY FROM now() - time_release) > hepaTime 
	OR 
	EXTRACT(DAY FROM now() - time_release) > normalTime );
END;
$BODY$;

INSERT INTO users (
		id,
        delete_status,
        gmail,
        password,
        username,
        working
    )
VALUES (
		1,
        1,
        'maylockhongkhihanoi68@gmail.com',
        '$2a$10$lTkVKyujirWeBDFHgcj36OwlrML0Fw5nwWGRKmxIl3q9IbilL0QiO',
        'maylockhongkhi',
        0
    );

INSERT INTO roles(id, delete_status ,name) VALUES(1,1, 'ROLE_USER');
INSERT INTO roles(id, delete_status ,name) VALUES(2,1, 'ROLE_ADMIN');

INSERT INTO public.users_roles(
	roles_id, users_id)
	VALUES (2, 1);