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

![Entities Model Diagram](http://www.plantuml.com/plantuml/png/hLHDRzim33rFluB8lK0xxB0MWT6FeLs77efrmx0Z4zEE5fao9Bf1jlM_JvPhoqaSe8STzTxp8nz5-T8BEcagevHVQwxHOGLvhU7zwUa8MHhq_ah4wKKZT6jPdlHlWey74kViPg3Fz-c1EHkRiyt9KmtdOz0XhrlmmwbQwPp02pcoigr9xRl27_Nk6fVhqjc6ynjhh8CVAorq0C_dTrXHbi63BbVog7az8VdkoVMIQzDCkToWotl9dIFY2SqzFsj1qPPxvKyoncxKowX_lyBSRZINKA3nEz7gO8WbpUSFkBMDP5c82R2mBYVNPVpca7qG4qlBf0OcsCAoJE5fhBUJYXUbkXctKu5-EB4LW2OS62k22tWI5r94j0xoJMXp5wKAjKbGy7qD69qzf9oTTrVrqU3siExnsiVhfnuEu2BCp0UuC1PbGCjWxY_2pNc1k3O-IryuJPmFRDTbHuTWgVshlDBy7wSM2wGxQTSGkcqSt7NhSCz29Ra8S5Cjo0szlb9PSccpGVSoZhjuGMdyswmZzolSRcrbPworqqjw91Vx7XEB4Cu6P66jYO3HJEDp_4jTjMwVK2X2XMRjLvGFQ4gKdXwCt-6GhYBliToxWAKZb3sxaT6iGjxEu2I3sTcityim3WzFn0BPMyHVQKdwcOw8EtMyZg2z9cEvzAssHI8rFp5Ko4d_HHnZKiwLwYe5RMytY3vZi4g2oP9VhEQHAj1_i_WwbUeMEPqlYVEcCly0)