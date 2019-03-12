# WEBingo - Web Bingo - API

[![Build Status](https://travis-ci.org/rhizomik/webingo-gei-api.svg?branch=master)](https://travis-ci.org/rhizomik/webingo-gei-api/branches) 

## Vision

**For** players **who** want to play bingo's game with his friends

**the project** WEBingo **is a** webapp created with Spring and Angular.

**that** allows them to create games and join to other games what are created from other users.

**Unlike** existing tools, WEBingo is multiplatform and only requires a device with one browser.


## Features per Stakeholder

|            Player               |             Admin               |                          Game Runner                          |
| --------------------------------| --------------------------------| --------------------------------------------------------------|
| Register/Edit profile           | Create, edit and remove players | Schedule a game at a specific time and with a specific reward |
| Call Line or Bingo              | Create, edit and remove game    | Check the lines and bingo cards                               |
| Join a game and buy one or several bingo cards| List past games and winners| Generate random bingo cards                           |
| List current/past games         | List current games              | Calculate price per win                                       |
| Send invitation game            | List total funds                | Give and withdraw money to players (if you win or buy carton) |
| Accept/Refuse invitation        |                                 | Start/finish a game                                           |
| Withdraw/Deposit money          |                                 |                                                               |

## Entities Model

![Entities Model Diagram](http://www.plantuml.com/plantuml/png/hLDDJnin43tNlyBA7Gcg7WgALdnKK2GEgBIgUdJYoSQDTxmQpnAbbFzUUytQ-TX877hqUyzlvet7bvuLSTTO8VpAOAj8DLAtHduyFTr2vbPvVyLCPjOnt3ha9_C7vES32P5RP_fydyxCsROvEWqUMdc-3PB2LGG_dOgbqI0z0u7ZJGjYtrM-Y0_NQhwgoNMeRvnr97yk3SC1F9tUgGQgIZwOUicF1bSZaXyUQ91SssxCvKQH7YHt180ZcdjyDgpOE1oKly1QjnQlM_txfT9kRR2M2sNzJhGs6AeQfjC7jN4T1vCN8UNCaGPA6R-JGX_4W1oPqi08kt38OtWPw-seubM8DECu5Pc74rkHiWi7L0t82_d455BqQ1laQqTw5uL66LkWuFiMi7VsikHCtgdgerMRmxh7Qn-lNteuW1TXPZx02-iKPxGEvluYl9ndsQ-DhyeDCe0wiwdBH8TWfVqhtHZyVrDB1SgRn3MKUHkJkuaEzyXG0_K0TiqCAFVwHbN59MQJwMSST_6ikFFlj8xSx_2whUETiRPavl0851lU8MOXd0l8p3dR0zOWv7CgJtmLtRueXXvQ63H-2JgZ9L7vUY5NEUHq3NYlwhq7c1CethFRCfeq2ZSJULB9oTbasCim3Y-V05bMxn5_WpcOPpWYVbFVfTMu1kkmzb7R8v6QdbXOyCbm8uqnAwT2f4f1EzXbqLSLh8fWf6JIj56WYpHe_bRz5nKYRNCvNmBghh7_0000)