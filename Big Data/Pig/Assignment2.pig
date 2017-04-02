
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

-- Question3

A = load '/hxp151330/Assignment1/review.csv' AS line ;

A1 = FOREACH A GENERATE FLATTEN((tuple(chararray,chararray,chararray,float))REGEX_EXTRACT_ALL(line,'(.*)\\:\\:(.*)\\:\\:(.*)\\:\\:(.*)')) AS (review_id,user_id,business_id,stars);

B = LOAD '/hxp151330/Assignment1/business.csv' AS line;

B1 = FOREACH B GENERATE FLATTEN((tuple(chararray,chararray,chararray))REGEX_EXTRACT_ALL(line,'(.*)\\:\\:(.*)\\:\\:(.*)')) AS (business_id,fullAddress,categories);

C = COGROUP A1 by business_id, B1 by business_id;

D = limit C 5 ;

Dump D;

-- Question4

A = load '/hxp151330/Assignment1/review.csv' AS line ;

A1 = FOREACH A GENERATE FLATTEN((tuple(chararray,chararray,chararray,float))REGEX_EXTRACT_ALL(line,'(.*)\\:\\:(.*)\\:\\:(.*)\\:\\:(.*)')) AS (review_id,user_id,business_id,stars);

B = LOAD '/hxp151330/Assignment1/business.csv' AS line;

B1 = FOREACH B GENERATE FLATTEN((tuple(chararray,chararray,chararray))REGEX_EXTRACT_ALL(line,'(.*)\\:\\:(.*)\\:\\:(.*)')) AS (business_id,fullAddress,categories);

C = FILTER B1 BY (fullAddress matches '.*Stanford, CA.*');

D = join C by business_id, A1 by business_id;

E = foreach D generate user_id, stars;

F = limit E 10;

dump F;
