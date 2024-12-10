#!/bin/bash
#
# Start local dev
#
echo "Starting PostgreSQL"

echo "host    all             all             0.0.0.0/0               trust" >> /etc/postgresql/14/main/pg_hba.conf

# service postgresql start
# psql -U postgres -c "ALTER ROLE postgres PASSWORD 'res74984'" -h localhost -p 5432
# service postgresql stop

sudo -u postgres /usr/lib/postgresql/14/bin/postgres -h 0.0.0.0 -D /var/lib/postgresql/14/main -c config_file=/etc/postgresql/14/main/postgresql.conf

# tail -f /dev/null