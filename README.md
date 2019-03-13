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

![Entities Model Diagram](http://www.plantuml.com/plantuml/png/hPJFZjem4CRFzLECkA_4LZrqLr6qiDNIbVQ0Igkgnu5CWejZH_Pa4TtoxdKSWWC4QG-zuMz-VFF7upmuHijrgOHm6wahj5X2LaduE1xtb9L2voRCLYvhfYUZUI7_47o-GAmrssFuxZnyZDpspRN9o545TttHejuqugUnMCkCm35PChohI9oxmfluCCNLfh2crjcZKSR2pxLakf2JP8ObfIcyo6BDSwat0yWFHxP3fgeUSdb4ctN8p1Bf0UPPlqf6baPtv2zIocp5lbU_MsDcjb8NaADo9wrLtX0BIf8Nt9cQlScR05WQcv5jU_nkKJiFa-Oc4WiOYEP6yv0Un_eqIEo5Q6VSJ0MwuOHI06f_q5WItCE2hUyYg9N7jxxCKvLAb2fAtlVGO71s4FjilTkiSuMxoxpNSr_FVr53b7C_C-VbN1daeEv5huP9Lahz7riF2U9Wcrs2uqgrxh9nUDPC1TaWwBfSadNtVf4SGt88fT6bsMlerlkQYsFashtFEiH--okg39_Cfh9oHNEoeUv-OEcRCrvP6gE2eAIc_ZbyQYQDsnTa2b8kjNHhog9wx2Y-7-W_fcDt9Jc7nTa5h2mXdzdrZ4Obwjq8Rb8OtOwwvVBZSB0WpP2-1_v6AvAlT0K-qBDsDQQaZ2vSmmQb2IKtYdA-wVuHnXZ9H8WsKyDsTXtq5RrL16BAyBFB6h2gOR98U-nl6fwQ4Er2n_C3wQmkrJy0)