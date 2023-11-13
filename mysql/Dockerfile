FROM mysql:5.7

# 設定ファイルをコンテナにコピー及び権限を変更
COPY ./my.cnf /etc/mysql/my.cnf
RUN chmod 644 /etc/mysql/my.cnf
# データの初期化を行うDDLをコンテナにコピー
COPY ./sql_init /docker-entrypoint-initdb.d