
CREATE OR REPLACE FUNCTION orderOnSchedule(hepaTime integer, normalTime integer)
  RETURNS SETOF public.order_schedules AS
$BODY$
BEGIN
  return query
	select * from order_schedules 
	where 
	status_order_schedule like 'đang chờ' 
	and
	(product_name like '%hepa%' 
	and 
	EXTRACT(DAY FROM now() - time_release) > hepaTime 
	OR 
	EXTRACT(DAY FROM now() - time_release) > normalTime );
END;
$BODY$
LANGUAGE plpgsql VOLATILE;
