#!/bin/bash

MC=${HOME}/mc
BUCKET='do/golden-master'
URL='https://dl.minio.io/client/mc/release/linux-amd64/mc'
ENDPOINT='https://ams3.digitaloceanspaces.com/'
LOCAL_PATH='target/test-classes/retest/recheck/'
HASH='c2622a22b60f4b5c77b06db960758377'

echo "Downloading minio mc ..."
wget --quiet --output-document=${MC} ${URL}
chmod +x ${MC}
echo ${HASH} ${MC} | md5sum --check -

echo "Preparing minio mc ..."
${MC} --quiet config host add do ${ENDPOINT} ${S3_ACCESS_KEY} ${S3_SECRET}

echo "Uploading to S3 ..."
${MC} --quiet cp --recursive ${TRAVIS_BUILD_DIR}/${LOCAL_PATH} ${BUCKET}/${TRAVIS_REPO_SLUG}/${TRAVIS_BRANCH}

echo "Cleaning up config ..."
rm -rf ${HOME}/.mc
