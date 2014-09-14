import subprocess
import time
import sys

def duration(source):
  start = time.time()
  subprocess.call(["scalac", source, "-d", "testclass"])
  return time.time() - start

for source in sys.argv[1:]:
  durations = map(duration, [source] * 5)
  print source
  print min(durations)
