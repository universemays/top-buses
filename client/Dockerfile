FROM node:16-alpine AS deps
WORKDIR /app
COPY package.json yarn.lock ./
RUN yarn install --frozen-lockfile

FROM node:16-alpine AS builder
WORKDIR /app
COPY --from=deps /app/node_modules ./node_modules
COPY . .

RUN yarn build

FROM node:16-alpine AS runner
WORKDIR /app

ARG APP_ENV=production
ARG NODE_ENV=production
ARG PORT=3000

ENV APP_ENV=${APP_ENV} \
    NODE_ENV=${NODE_ENV} \
    PORT=${PORT}

RUN addgroup --system --gid 1001 nextjs
RUN adduser --system --uid 1001 app

USER app

COPY --from=builder /app/next.config.js ./
COPY --from=builder /app/public ./public
COPY --from=builder --chown=app:nextjs /app/.next/standalone ./
COPY --from=builder --chown=app:nextjs /app/.next/static ./.next/static
COPY --from=builder /app/package.json ./package.json

EXPOSE ${PORT}

ENV PORT ${PORT}

CMD ["node", "server.js"]