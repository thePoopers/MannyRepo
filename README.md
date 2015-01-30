# MannyRepo
Must edit DocumentRoot:
	- Edit /apache2/sites-enabled/000-default.conf
	- Change DocumentRoot to your www/html directory
	- Save

Edit /etc/apache2/apache2.conf and add the following:

<Directory /home/vg/www>
    Options Indexes FollowSymLinks
    AllowOverride None
    Require all granted
</Directory>


Reload Apache

Configure conf.inc.php to connect to MYSQLDB

