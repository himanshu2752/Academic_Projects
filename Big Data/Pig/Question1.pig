-- Question1

A = load '/hxp151330/Assignment1/review.csv' AS line ;

A1 = FOREACH A GENERATE FLATTEN((tuple(chararray,chararray,chararray,float))REGEX_EXTRACT_ALL(line,'(.*)\\:\\:(.*)\\:\\:(.*)\\:\\:(.*)')) AS (review_id,user_id,business_id,stars); 

B = LOAD '/hxp151330/Assignment1/business.csv' AS line;

B1 = FOREACH B GENERATE FLATTEN((tuple(chararray,chararray,chararray))REGEX_EXTRACT_ALL(line,'(.*)\\:\\:(.*)\\:\\:(.*)')) AS (business_id,fullAddress,categories);

C = Join B1 by business_id, A1 by business_id;

D = FILTER C BY fullAddress matches '.*Palo Alto, CA.*';

E = GROUP D BY( A1::business_id, B1::fullAddress, B1::categories);

F = foreach E generate group, AVG(D.(A1::stars)) as avgRating;

G = order F by avgRating desc;

H = limit G 10;

dump H;