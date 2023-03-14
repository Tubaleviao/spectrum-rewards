import React, { useState, useEffect } from "react";
import axios from "axios";
import {
  Container,
  Typography,
  TableContainer,
  Table,
  TableHead,
  TableBody,
  TableRow,
  TableCell,
  Paper,
  CircularProgress,
  Snackbar,
  Button,
  Box,
} from "@mui/material";
import { Receipt, Delete, Add } from "@mui/icons-material";
import MuiAlert from '@mui/material/Alert';

const Alert = React.forwardRef(
  function Alert( props, ref, ) {
    return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
  }
);

function App() {
  const [transactions, setTransactions] = useState([]);
  const [rewards, setRewards] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [info, setInfo] = useState({});
  const [showMessage, setShowMessage] = useState(false);
  const baseUrl = "http://localhost:8080"

  // Get data from out Springboot API
  useEffect(() => {
    async function fetchData() {
      try {
        const transactionsResponse = await axios.get(baseUrl+"/api/transactions");
        setTransactions(transactionsResponse.data);
      } catch (error) {
        setInfo({message: error.message, type: "error"})
        setShowMessage(true)
        console.error(error);
      } finally {
        setIsLoading(false);
      }
    }
    fetchData();
  }, []);

  useEffect(() => {
    async function fetchData() {
      try {
        const rewardsResponse = await axios.get(baseUrl+"/api/rewards");
        const rewardMonths = rewardsResponse.data.map(r => {
          r.months = r.months.map((points,i) => {
            const date = new Date()
            date.setMonth(date.getMonth()-(2-i))
            const month = date.toLocaleString('default', { month: 'long' })
            return {"month": month, "points": points}
          })
          return r
        })
        setRewards(rewardMonths)
      } catch (error) {
        setInfo({message: error.message, type: "error"})
        setShowMessage(true)
        console.error(error);
      } finally {
        setIsLoading(false);
      }
    }
    fetchData();
  }, [transactions]);

  // Create functions for handing clicks or events
  const closeInfo = () => setShowMessage(false)
  const addRandomTransaction = () => {
    const customer = {id: 1, name: "Alvaro"}
    const value = (200*Math.random()).toFixed(2)
    const date = new Date()
    const transaction = {customer, name: "test-transaction", value, date  }
    setIsLoading(true);
    axios.post(baseUrl+"/api/transactions", transaction).then(
      () => {
        setTransactions( [ ...transactions, transaction ])
        setInfo({message: `Transaction ${transaction.name} successfuly created!`, type: "success"})
        setShowMessage(true)
      }
    ).catch(e => {
      setInfo({message: e.message, type: "error"})
      setShowMessage(true)
    })
    .finally(() => setIsLoading(false));
  }
  const handleDelete = (transaction) => () => {
    setIsLoading(true);
    axios.delete(baseUrl+"/api/transactions", {data: transaction}).then(
      () => {
        setTransactions( transactions.filter(t => t.id !== transaction.id ))
        setInfo({message: `Transaction ${transaction.name} successfuly deleted!`, type: "success"})
        setShowMessage(true)
      }
    ).catch(e => {
      setInfo({message: e.message, type: "error"})
      setShowMessage(true)
    })
    .finally(() => setIsLoading(false));
  }

  // Build Interface using Material UI
  return (
    <Container maxWidth="lg">
      <Snackbar anchorOrigin={{ vertical: "top", horizontal: "right" }}
        autoHideDuration={5000} 
        onClick={closeInfo} onClose={closeInfo} open={showMessage}>
        <Alert severity={info.type}> {info.message} </Alert>
      </Snackbar>
      <Box sx={{ m: 1 }}>
        <Typography variant="h4" align="center" gutterBottom>
          <Receipt fontSize="large" /> All Transactions
        </Typography>
        <Box textAlign='center'>
          <Button variant="contained" onClick={addRandomTransaction} >
              <Add fontSize="large"/>
              Add Test Transaction
          </Button>
        </Box>
        {isLoading ? (
          <CircularProgress />
        ) : (
          <TableContainer component={Paper}>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell>Customer</TableCell>
                  <TableCell align="right">Transaction Name</TableCell>
                  <TableCell align="right">Value</TableCell>
                  <TableCell align="right">Date</TableCell>
                  <TableCell align="right">Actions</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {transactions.map((transaction) => (
                  <TableRow key={transaction.id}>
                    <TableCell component="th" scope="row">
                    {transaction.customer.name}
                    </TableCell>
                    <TableCell align="right">{transaction.name}</TableCell>
                    <TableCell align="right"> {transaction.value} </TableCell>
                    <TableCell align="right">{new Date(transaction.date).toLocaleString()}</TableCell>
                    <TableCell align="right"> <Delete fontSize="medium" onClick={handleDelete(transaction)} /></TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        )}
      </Box>
      <Box sx={{ m: 1 }}>
        <Typography variant="h4" align="center" gutterBottom>
          <Receipt  fontSize="large" /> All Rewards
        </Typography>
        {isLoading ? (
          <CircularProgress />
        ) : (
          <TableContainer component={Paper}>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell>Customer</TableCell>
                  <TableCell align="right">Points per Month</TableCell>
                  <TableCell align="right">Total Points in 3 Months</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {rewards.map((rewards, i) => (
                  <TableRow key={`reward-${i}`}>
                    <TableCell component="th" scope="row">
                    {rewards.customer.name}
                    </TableCell>
                    <TableCell align="right">
                      {rewards.months.map(m => `${m.month}: ${m.points}`).join("; ")}
                    </TableCell>
                    <TableCell align="right"> {rewards.months.reduce((acc,obj) => acc+obj.points, 0)} </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        )}
      </Box>
    </Container>
  );
}

export default App;
