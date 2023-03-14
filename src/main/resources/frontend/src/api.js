const BASE_URL = 'http://localhost:8080/api';

async function fetchJson(url, options = {}) {
  const response = await fetch(url, options);
  if (!response.ok) {
    throw new Error(`Error ${response.status}: ${response.statusText}`);
  }
  const data = await response.json();
  return data;
}

export function getTransactions() {
  return fetchJson(`${BASE_URL}/transactions`);
}

export function getUserRewards(userId) {
  return fetchJson(`${BASE_URL}/users/${userId}`);
}
