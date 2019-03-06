FROM nimmis/apache-php7

MAINTAINER gr33n5h4d0w <contact@faizalhasanwala.me>

WORKDIR /var/www/html/

COPY changeOwner changeOwner
COPY index.php index.php
COPY flag.txt /flag.txt
COPY upload.php upload.php
COPY includes includes

RUN chown -R root:www-data .
RUN chmod -R 755 .

RUN chmod 1775 includes
RUN chmod u+s changeOwner

RUN rm index.html
EXPOSE 8080 80/tcp

CMD ["/usr/sbin/apache2ctl", "-D", "FOREGROUND"]