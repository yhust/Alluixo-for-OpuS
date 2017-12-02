#!/bin/bash
alluxio fs rm /sample/*
alluxio fs copyFromLocal ~/Desktop/sample.txt /sample/sample-1
for i in {2..60}
do
	   alluxio fs cp /sample/sample-1 /sample/sample-${i}
done
