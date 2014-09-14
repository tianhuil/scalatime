import subprocess
import time

def duration(i):
  start = time.time()
  subprocess.call(["scalac", "HelloWorld.scala"])
  return time.time() - start

durations = map(duration, xrange(5))
print min(durations)
