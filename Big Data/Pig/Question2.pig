-- Question2

A = load '/hxp151330/Assignment1/review.csv' AS line ;

A1 = FOREACH A GENERATE FLATTEN((tuple(chararray,chararray,chararray,float))REGEX_EXTRACT_ALL(line,'(.*)\\:\\:(.*)\\:\\:(.*)\\:\\:(.*)')) AS (review_id,user_id,business_id,stars);

B = LOAD '/hxp151330/Assignment1/business.csv' AS line;

B1 = FOREACH B GENERATE FLATTEN((tuple(chararray,chararray,chararray))REGEX_EXTRACT_ALL(line,'(.*)\\:\\:(.*)\\:\\:(.*)')) AS (business_id,fullAddress,categories);

C = FILTER B1 BY (fullAddress matches '.*, CA.*') and not (fullAddress matches '.*Palo Alto, CA.*') and not (fullAddress matches '.*palo alto, ca.*');

D = Join A1 by business_id, C by business_id;

E = GROUP D BY( C::business_id, C::fullAddress, C::categories);

F = foreach E generate group, AVG(D.(A1::stars)) as avgRating;

G = order F by avgRating desc;

H = limit G 10;

dump H;