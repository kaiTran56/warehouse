INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_STAFF');
INSERT INTO roles(name) VALUES('ROLE_OPERATOR');

-- FUNCTION: public.orderonschedule(integer, integer)

-- DROP FUNCTION public.orderonschedule(integer, integer);

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