#!/bin/bash

mvn clean verify sonar:sonar \
  -Dsonar.projectKey=job_vacancy_manager \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=sqp_4fafd074a4442638b44672859d320db5e4277151