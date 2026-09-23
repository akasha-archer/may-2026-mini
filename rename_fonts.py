import os
import re

font_dir = '/Users/akashaarcher/may2026project/app/src/main/res/font/'

def camel_to_snake(name):
    s1 = re.sub('(.)([A-Z][a-z]+)', r'\1_\2', name)
    return re.sub('([a-z0-9])([A-Z])', r'\1_\2', s1).lower()

for filename in os.listdir(font_dir):
    if filename.endswith('.ttf'):
        basename = filename[:-4]
        # Custom logic for "DMSans"
        basename = basename.replace('DMSans', 'DmSans')
        
        # Replace dash with underscore
        basename = basename.replace('-', '_')
        
        # Convert camel case to snake case
        new_basename = camel_to_snake(basename)
        
        # Replace any multiple underscores with single
        new_basename = re.sub(r'__+', '_', new_basename)
        
        # Final cleanup to ensure only a-z0-9_
        new_basename = re.sub(r'[^a-z0-9_]', '_', new_basename)
        
        new_filename = new_basename + '.ttf'
        if filename != new_filename:
            old_path = os.path.join(font_dir, filename)
            new_path = os.path.join(font_dir, new_filename)
            os.rename(old_path, new_path)
            print(f'Renamed: {filename} -> {new_filename}')
