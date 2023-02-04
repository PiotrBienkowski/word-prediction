for i in {27..35}; do
    echo $i|python3 tmp_script.py > tmp
    cat tmp
    echo "------------------------------------------------------------------";
    python3 scrap.py < tmp
done;
rm tmp