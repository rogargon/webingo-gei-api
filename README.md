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

![Entities Model Diagram](http://www.plantuml.com/plantuml/png/hLDDRzim33tFluB8lK0wxB0MWT6aGxCEFHJhXc579QOTBJ9bI7I3xEE_Zx8GoscSOOVUhCUdn_Se-iQpSjpM9ilyLbEZdAgXQ3IydKu7oDeex-VCJgzQnZjB_AH_8hm_eJXdTttvwcMvhrmEnSaMwB61go7e56q3-6wQRNI1u1aTMjutcBrKXL_PcuLQRojdMofkhR4ElcqqumaycorLZNaE3xhQyAEcxGZbgqTte2nCEwPogrnne2mT8ermxkbPis9jwS3yZiRONVPduDzlL65tcYeebV57qHeHL1NEPWzgRrlESua9iBAkG1SpVd6Al92HE5IIWP5gQOd7y3JMkp66cCtYZCDKe1zEi0BGoe5KZN0DJ-maHOSsGj-9pMCKQwLDWaGt1iHEsKFA6RLZrqUZzgTzp_S-tpzvE85BcPaNk3HMSOzM8kwlvUKyGxSsFaytd4Og-cfq6SiIBDcV5xMcrvjQR93U9AmXzDiOrNLGk2V62bqKTdgDH-cehLVeUlUHxFAa6z92_5VEwtfMtFe4_bCwr7x8UpUMZwoi99KLP6MjwG2Z2OVdjKF5MCp3jG_orO6b9kqt0pHPJt-T14Y7FaQDtgigpNwyYSWDX2Qrelq4BYvWSZavhAG88Cdq_uVv6TUedn7oKRB1akE5PToA1HfBbG_qW4YfPyKxyU4JKKp4Nf4Qn7hEHoL2gPuCfdwVhAOZ4WJ6xxpx2AkRPN6HHmetI4LRcxy0)
