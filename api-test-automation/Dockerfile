FROM python:3.11-slim

WORKDIR /app

ENV PYTHONPATH=/app
ENV TEST_ENV=docker

COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

COPY . .

CMD ["pytest", "-v"] 