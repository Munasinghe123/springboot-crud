import React from 'react'
import hero from './assets/heroImage3.jpg'
import icon from './assets/seethrough.png'

function Landing() {
  return (
    <section className='relative'>
      <div className='lg:h-[90vh] h-[100vh] bg-cover bg-center '
        style={{ backgroundImage: `url(${hero})` }}/>

       
          <div className=' relative md:absolute max-w-2xl p-4 md:bottom-20 md:left-6  lg:bottom-40 lg:left-40  bg-green-500'>
            Before you can begin to determine what the composition of a particular paragraph will be, you must 
            first decide on an argument and a working thesis statement for your paper. What is the most important 
            idea that you are trying to convey to your reader? The information in each paragraph must be related to that idea. 
            In other words, your paragraphs should remind your reader that there is a recurrent relationship between your thesis
             and the information in each paragraph. A working thesis functions like a seed from which your paper, and your ideas, will grow.
             The whole process is an organic one—a natural 
            progression from a seed to a full-blown paper where there are direct, familial relationships between all of the ideas in the paper.
          </div>

        <img src={icon} className='bottom-3 right-5 fixed h-30 w-30'/>
    </section>
  )
}

export default Landing
