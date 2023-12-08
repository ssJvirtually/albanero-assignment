@echo off

set services=order shipment payment

for %%a in (%services%) do (
  echo Building jar for %%a service...
  pushd %%a
  mvn package -DskipTests
  popd
  echo Jar built for %%a service!
)

echo All jars built successfully!
pause
