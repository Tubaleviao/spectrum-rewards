
FROM node AS rewards-build
RUN mkdir /build
RUN git clone https://github.com/Tubaleviao/spectrum-rewards.git build
